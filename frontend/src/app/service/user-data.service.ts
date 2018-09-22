import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Expense } from '../model/Expense';
import { map } from 'rxjs/operators'

@Injectable({
  providedIn: 'root'
})
export class UserDataService {
  loggedUser: any = {"principal": {"id":'', "monthBudget": ''}};
  userId: any;
  
  constructor(private http: HttpClient) {

  
  }
  getUserData(){
  return  this.http.get("/user").pipe(map((data => {
      if(data){
       this.loggedUser = data
         }
       else console.log("error");
   }))).toPromise().then(data => this.userId = this.loggedUser.principal.id);
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

  deleteExpense(id){
    return this.http.delete("users/"+this.userId+"/expenses/"+id);
  }

  getMonthSum(month){
    return this.http.get("users/"+this.userId+"/expenses/bymonth/"+month+"/sum");
  }
}
