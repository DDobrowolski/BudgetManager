import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class UserDataService {
  loggedUser: any = {"principal": {"id":''}};
  userId: any;
  
  constructor(private http: HttpClient) {
     this.http.get("/user").subscribe(data => {
      this.loggedUser = data
       if(data){
        this.userId = this.loggedUser.principal.id;
          }
    });
  }

     getData(){
      return this.http.get("users/"+this.userId+"/expenses/categorysum");
  }

  getDataByDate(date:String){
    return this.http.get("users/"+this.userId+"/expenses/bydate/"+date+"/categorysum");
  }
}
