
export interface CustomerProject { 
    projectName : string,
    projectDomain : string,
    projectDesc : string ,
    projectStatus : string,
    customerProfile : CustomerProfile
}

interface CustomerProfile {
    customerProfileId : number
}