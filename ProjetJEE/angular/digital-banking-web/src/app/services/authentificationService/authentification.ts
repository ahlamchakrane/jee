import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Router } from "@angular/router";
import { JwtHelperService } from "@auth0/angular-jwt";
import { environment } from "src/environments/environment";

@Injectable()
export class AuthenticationService{
    private host:string="http://localhost:8083";
    private jwtToken!: string;
    private jwtHelper=new JwtHelperService();
    private roles: Array<any>=[];
    private username!:string;
    public oui = false;
    constructor(private http:HttpClient, private router : Router){
    
    }   
    
    login(user : any){
        return this.http.post(this.host+"/login",user,{ observe: 'response' });       
    }
    saveToken(jwt:string){
        this.jwtToken=jwt;
        localStorage.setItem('token',jwt);
       
        this.roles=this.jwtHelper.decodeToken(this.jwtToken).roles;
        this.username=this.jwtHelper.decodeToken(this.jwtToken).sub;
        
    }
   isLoggedIn() : boolean{
        if(localStorage.getItem('token')==null)
            return false;
        else
            return true;
    }
    loadToken(){
        this.jwtToken=localStorage.getItem('token')!;
        return this.jwtToken;
    }
    logout(){
        this.jwtToken=null!;
        localStorage.removeItem('token');
        this.oui=false;
        this.router.navigateByUrl("/login");
    }
    isAdmin(){
        let i=localStorage.getItem('token');
        if(i!=null)
        this.roles=this.jwtHelper.decodeToken(i!).roles;
        for(let r of this.roles){
            if(r=='ADMIN')  return true;
        }
        return false;
    }
    getRole(){
        if(this.isAdmin()) return "administrateur";
        return "utilisateur";
    }
    getUsername(){
        let i=localStorage.getItem('token');
        if(i!=null)
        this.username=this.jwtHelper.decodeToken(i!).sub;
        return this.username;
    }
}