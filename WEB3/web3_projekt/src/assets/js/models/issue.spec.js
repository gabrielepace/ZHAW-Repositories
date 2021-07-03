describe('Issue Model', () => {
    it('marks an issue as completed', () => {
        const issue = new Issue('my-uuid-4-id', 'high', 'test issue', '2019-11-18T22:40:00Z', false);
        expect(issue.done).toEqual(false);

        issue.setDone(true);
        expect(issue.done).toEqual(true);

        issue.setDone(false);
        expect(issue.done).toEqual(false);
    });
});
