export interface CustomerProfile {
    customerName : string,
    customerCompany:string,
    customerPhone:string,
    isActive:boolean,
    user :  User
  }

  interface User{
    userName:string
  }