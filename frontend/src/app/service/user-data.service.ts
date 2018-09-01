import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { ReturnStatement } from '@angular/compiler';
import { Expense } from '../model/Expense';

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

  getExpensesData(date: String){
    return this.http.get("users/"+this.userId+"/expenses/bydate/"+date);  
  }

  addExpense(expense: Expense){
    return this.http.post("users/"+this.userId+"/expenses", expense);
  }
}
