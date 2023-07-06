import { Role } from "./role.model";

export interface User {
  userName: string;
  userPassword: string;
  userRole: Set<Role>;
}
