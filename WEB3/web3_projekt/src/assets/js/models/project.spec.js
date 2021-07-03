describe('Project Model', () => {
    let project;
    beforeEach(() => {
        project = new Project('project-id-4', 'web3-project', [
            new Issue('issue-id-4', 'high', 'test issue', '2019-11-18T22:40:00Z', true),
        ]);
    });

    it('instantiates a project correctly', () => {
        expect(project.id).toEqual('project-id-4');
        expect(project.title).toEqual('web3-project');
        expect(project.issues.length).toEqual(1);
        expect(project.issues[0].title).toEqual('test issue');
    });

    it('finds an issue by id', () => {
        const issue = project.getIssueById('issue-id-4');

        expect(issue).not.toBeNull();
        expect(issue.id).toEqual('issue-id-4');
    });
});