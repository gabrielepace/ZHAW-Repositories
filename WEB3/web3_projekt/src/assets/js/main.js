(async function main() {
  await riot.compile();

  riot.mount('app, navigation, pagefooter, projects-list, create-project, issues-list, create-issue, loading');
}())
