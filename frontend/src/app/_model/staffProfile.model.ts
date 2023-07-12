export interface StaffProfile {
  staffProfileId: number,
  staffName: string,
  staffPhone: string,
  staffEmail: string,
  isActive: boolean,
  user: User
}

interface User {
  userName: string
}
