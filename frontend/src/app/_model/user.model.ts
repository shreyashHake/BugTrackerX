import { Role } from "./role.model";

export interface User {
  userName: string;
  userFirstName: string;
  userLastName: string;
  userPassword: string;
  userRole: Set<Role>;
}
