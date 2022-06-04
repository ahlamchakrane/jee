import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Router} from "@angular/router";
import { environment } from 'src/environments/environment';
import { JwtHelperService } from '@auth0/angular-jwt';

@Injectable({
  providedIn: 'root'
})
export class LoginService {
  public oui = false;
  headers: any;
  private jwtToken!: string;
  private jwtHelper=new JwtHelperService();
  private roles: Array<any>=[];

  constructor(private http: HttpClient, private router: Router) {
  }

  public login(username: string, password: string): any {
    let input = new FormData();
    input.append('username', username);
    input.append('password', password);
    this.oui=true;
    return this.http.post(environment.backendHost + "/login", input);
    
  }

  public logout() {
    this.oui=false;
    localStorage.removeItem("accessToken");
    localStorage.removeItem("refreshToken");
    localStorage.removeItem("roles");
    localStorage.removeItem("id");
    this.router.navigateByUrl('/login');
  }

  public refreshToken() {
    this.headers = {'Authorization': 'Bearer ' + localStorage.getItem("refreshToken")};
    console.log("ref"+localStorage.getItem("refreshToken"));
    this.http.get(environment.backendHost + "/refreshToken", {headers: this.headers}).subscribe({
      next: (data: any) => {
        localStorage.setItem("accessToken", data.accessToken);
        localStorage.setItem("refreshToken", data.refreshToken);
        localStorage.setItem("roles", data.roles);
        location.reload();
      },
      error: (err: any) => {
        console.log(err);
      }
    });
  }
  isAdmin(){
      let i=localStorage.getItem('roles');
      if(i!=null)
          if(i=='ADMIN')  return true;
      return false;
  }
  
}
