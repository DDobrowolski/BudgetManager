import { Component, OnInit } from '@angular/core';
import {MatDatepickerModule, MatCalendarHeader} from '@angular/material/datepicker';
import { UserDataService } from '../service/user-data.service';
 
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
  constructor(private usersService: UserDataService) {
    

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
    console.log(date.toLocaleDateString());
    this.changeChart();
    this.ngOnInit();
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

}
