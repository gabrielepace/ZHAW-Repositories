import com.cloudbees.jenkins.plugins.sshcredentials.impl.BasicSSHUserPrivateKey
import com.cloudbees.plugins.credentials.CredentialsScope
import com.cloudbees.plugins.credentials.SystemCredentialsProvider
import com.cloudbees.plugins.credentials.impl.UsernamePasswordCredentialsImpl
import hudson.model.JDK
import hudson.security.HudsonPrivateSecurityRealm
import hudson.security.FullControlOnceLoggedInAuthorizationStrategy
import org.jenkinsci.plugins.github_branch_source.GitHubConfiguration
import org.jenkinsci.plugins.github_branch_source.Endpoint

import hudson.plugins.groovy.Groovy
import jenkins.model.Jenkins

String jenkinsHome = '/root'
Closure setCredsIfMissing = { String id, String descr, String user, String pass ->
	boolean credsMissing = SystemCredentialsProvider.getInstance().getCredentials().findAll {
		it.getDescriptor().getId() == id
	}.empty
	if (credsMissing) {
		println "Credential [${id}] is missing - will create it"
		SystemCredentialsProvider.getInstance().getCredentials().add(
			new UsernamePasswordCredentialsImpl(CredentialsScope.GLOBAL, id,
				descr, user, pass))
		SystemCredentialsProvider.getInstance().save()
	}
}
Closure setSshCredsIfMissing = { String id, String descr, String gitUser, String gitSshKey ->
	boolean credsMissing = SystemCredentialsProvider.getInstance().getCredentials().findAll {
		it.getDescriptor().getId() == id
	}.empty
	if (credsMissing) {
		println "Credential [${id}] is missing - will create it"
		SystemCredentialsProvider.getInstance().getCredentials().add(
			new BasicSSHUserPrivateKey(CredentialsScope.GLOBAL, id,
				gitUser, new BasicSSHUserPrivateKey.DirectEntryPrivateKeySource(gitSshKey), '', descr))
		SystemCredentialsProvider.getInstance().save()
	}
}

println "Creating the settings.xml file"
String m2Home = jenkinsHome + '/.m2'
File m2HomeFile = new File(m2Home)
m2HomeFile.mkdirs()
File mavenSettings = new File("${m2Home}/settings.xml")
if (m2HomeFile.exists()) {
	boolean settingsCreated = mavenSettings.createNewFile()
	if (settingsCreated) {
		mavenSettings.text = new File('/usr/share/jenkins/settings.xml').text
	} else if (mavenSettings.exists()) {
		println "Overridden existing maven settings"
		mavenSettings.text = new File('/usr/share/jenkins/settings.xml').text
	} else {
		println "Failed to create settings.xml!"
	}
} else {
	println "Failed to create .m2 folder!"
}

println "Creating artifactory credentials"
String repoWithBinariesCredId = "artifactory"
setCredsIfMissing(repoWithBinariesCredId, "artifactory",
	System.getenv('M2_SETTINGS_REPO_USERNAME') ?: "admin",
	System.getenv('M2_SETTINGS_REPO_PASSWORD') ?: "password")


println "Importing GPG Keys"
def privateKey = new File('/usr/share/jenkins/private.key')
def publicKey = new File('/usr/share/jenkins/public.key')

void importGpgKey(String path) {
	def sout = new StringBuilder(), serr = new StringBuilder()
	String command = "gpg --import " + path
	def proc = command.execute()
	proc.consumeProcessOutput(sout, serr)
	proc.waitForOrKill(1000)
	println "out> $sout err> $serr"
}

if (privateKey.exists()) {
	println "Importing private key from " + privateKey.getPath()
	importGpgKey(privateKey.getPath())
	privateKey.delete()
} else {
	println "Private key file does not exist in " + privateKey.getPath()
}

if (publicKey.exists()) {
	println "Importing public key from " + publicKey.getPath()
	importGpgKey(publicKey.getPath())
	publicKey.delete()
} else {
	println "Public key file does not exist in " + publicKey.getPath()
}

println "Adding jdk"
Jenkins.getInstance().getJDKs().add(new JDK("jdk8", "/usr/lib/jvm/java-8-openjdk-amd64"))

println "Create Github Enterprise Instance"
def gheApiEndpoint = new Endpoint("https://github.zhaw.ch/api/v3/","ZHAW Github Server")
GitHubConfiguration.get().updateEndpoint(gheApiEndpoint)

println "Marking allow macro token"
Groovy.DescriptorImpl descriptor =
	(Groovy.DescriptorImpl) Jenkins.getInstance().getDescriptorOrDie(Groovy)
descriptor.configure(null, net.sf.json.JSONObject.fromObject('''{"allowMacro":"true"}'''))

println "Create user and enable security"
def instance = Jenkins.getInstance()
def hudsonRealm = new HudsonPrivateSecurityRealm(false)
hudsonRealm.createAccount("jenkins","swordfish")
instance.setSecurityRealm(hudsonRealm)
def strategy = new FullControlOnceLoggedInAuthorizationStrategy()
strategy.setAllowAnonymousRead(false)
instance.setAuthorizationStrategy(strategy)
instance.save()

