import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class UserDataService {

  constructor(private http: HttpClient) {}

     getData(){
      return this.http.get("users/1/expenses/categorysum");
  }

  getDataByDate(date:String){
    return this.http.get("users/1/expenses/bydate/"+date+"/categorysum")
  }
}
