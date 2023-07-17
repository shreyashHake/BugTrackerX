
export interface Comment{
    commentNumber:number,
    comment:string,
    commentBy:string,
    user:{
        userName:string
    },
    bugProcess : {
        bugProcessId:number;
    }
}