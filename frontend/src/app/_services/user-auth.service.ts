import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class UserAuthService {
  static getToken() {
    throw new Error("Method not implemented.");
  }

  constructor() { }

  public setRole(role: string) {
    localStorage.setItem("role", role);
  }

  public getRole() {
    return localStorage.getItem("role");
  }

  public setToken(jwtToken: string) {
    localStorage.setItem('jwtToken', jwtToken);
  }

  public getToken() {
    return localStorage.getItem('jwtToken') || '';
  }

  public clearLocalStorage() {
    localStorage.clear();
  }

  public isLoggedIn(): boolean {
    return (this.getRole() != null) && (this.getToken() != null);
  }
}
