import { Component, OnInit } from '@angular/core';
import {MatDatepickerModule, MatCalendarHeader} from '@angular/material/datepicker';
import { UserDataService } from '../service/user-data.service';
import { AuthserviceService } from '../service/authservice.service';
import { Router } from '@angular/router';
import { Expense } from '../model/Expense';
 
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
  date:Date;
  expenses$: any = {TRAVEL:'', OTHERS: '', RELAX: '', SHOPPING: '', INSURANCE: '', HOUSE: '', FOOD: ''};
  expensesList$: any;
  expense: Expense = new Expense();
  
  constructor(private usersService: UserDataService, private authService:AuthserviceService, private router:Router) {
    if(!this.authService.isLogged)
      this.router.navigate(['/login']);
    else return;
    this.date.toLocaleDateString();
    }

  ngOnInit() {


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
  }

  changeChart(){
    this.usersService.getDataByDate(this.date.toLocaleDateString()).subscribe(
      data =>{ this.expenses$ = data
        if(data){
          let expenses2 = [this.expenses$.FOOD, this.expenses$.INSURANCE, this.expenses$.TRAVEL, this.expenses$.HOUSE, this.expenses$.RELAX, this.expenses$.SHOPPING, this.expenses$.OTHERS];
          this.pieChartData = expenses2;
        }
      }
    );
  }

  changeList(){
    this.usersService.getExpensesData(this.date.toLocaleDateString()).subscribe(
      data => {this.expensesList$ = data}
    )
  }
  addExpense(){
    this.usersService.addExpense(this.expense).subscribe(data => {
      this.ngOnInit();
    });
  }
}
