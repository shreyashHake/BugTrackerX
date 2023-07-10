export interface Bug {
    bugTitle: string,
    bugDesc: string,
    bugPriority: string,
    bugStatus: string,
    customerProject: {
        projectId: number
    }
}