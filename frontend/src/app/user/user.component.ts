import { Component, OnInit } from '@angular/core';
import {MatDatepickerModule, MatCalendarHeader} from '@angular/material/datepicker';
import { UserDataService } from '../service/user-data.service';
import { AuthserviceService } from '../service/authservice.service';
import { Router } from '@angular/router';
import { Expense } from '../model/Expense';


declare var $: any;
@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent implements OnInit {
  public pieChartLabels:Array<any> = ['Food', 'Insurance', 'Travelling', 'Housing', 'Relaxation', 'Shopping', 'Others'];
  public pieChartData:Array<Number> = [];
  public pieChartColors:Array<any> = [{backgroundColor: [
    '#ff9999', '#99b3ff', '#d1b3ff', '#b3fff0', '#669900', '#99ff99', '#ffcc99'
  ]}];
  public pieChartType:string = 'pie';
  
  date:Date = new Date();
  expenses$: any = {TRAVEL:'', OTHERS: '', RELAX: '', SHOPPING: '', INSURANCE: '', HOUSE: '', FOOD: ''};
  expensesList$: any;
  expense: Expense = new Expense();
  monthSum: any;
  monthBudget: any;
  
  
  constructor( private authService:AuthserviceService,private usersService: UserDataService, private router:Router) {
    if(!this.authService.isLogged)
      this.router.navigate(['/login']);
    else this.ngOnInit()
    }

  ngOnInit() {
    this.usersService.getUserData().then(data => {
      this.onChange(this.date);
      this.monthBudget = this.usersService.loggedUser.principal.monthBudget;
    });
  }

  public chartClicked(e:any):void {
    return;
  }
 
  public chartHovered(e:any):void {
    return;
  }
  
  onChange(date){
    this.changeChart();
    this.changeList();
    this.getMonthSum();
  }

  changeChart(){
    if(this.usersService.userId!=undefined){
    this.usersService.getDataByDate(this.date.toLocaleDateString(undefined, {day: '2-digit', month: '2-digit', year: 'numeric'})).subscribe(
      data =>{ this.expenses$ = data
        if(data){
          let expenses2 = [this.expenses$.FOOD, this.expenses$.INSURANCE, this.expenses$.TRAVEL, this.expenses$.HOUSE, this.expenses$.RELAX, this.expenses$.SHOPPING, this.expenses$.OTHERS];
          this.pieChartData = expenses2;
        }
      }
    );
  }
}

  changeList(){
    if(this.usersService.userId!=undefined){
    this.usersService.getExpensesData(this.date.toLocaleDateString(undefined, {day: '2-digit', month: '2-digit', year: 'numeric'})).subscribe(
      data => {this.expensesList$ = data}
    )
  }
}
  addExpense(){
    this.usersService.addExpense(this.expense).subscribe(data => {
      this.onChange(this.date);
    });
  }

  changeCategory(category: String){
    if(category === "FOOD")
      return "Food";
    if(category === "INSURANCE")
      return "Insurance";
    if(category === "TRAVEL")
      return "Travelling";
    if(category === "HOUSE")
      return "Housing";
    if(category === "RELAX")
      return "Relaxation";
    if(category === "SHOPPING")
      return "Shopping";
    if(category === "OTHERS")
      return "Others";
  }

  deleteExpense(expenseId){
    if(confirm("Do you want to delete the expense?")){
    this.usersService.deleteExpense(expenseId).subscribe(data => {
      this.onChange(this.date);
    });
    }
    else {}
  }
  getMonthSum(){
    this.usersService.getMonthSum(this.date.toLocaleDateString(undefined, {month: '2-digit', year: 'numeric'})).subscribe(data => this.monthSum = data);
  }

  openChangeBudgetModal(){
    $("#changeBudgetModal").modal();
  }
}
