import { Component, OnInit } from '@angular/core';
import {MatDatepickerModule, MatCalendarHeader} from '@angular/material/datepicker';
 
@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent implements OnInit {
  public pieChartLabels:Array<any> = ['Food', 'Insurance', 'Travelling', 'Housing', 'Relaxation', 'Shopping', 'Others'];
  public pieChartData:Array<Number> = [300, 500, 100, 44, 86, 22, 6];
  public pieChartColors:Array<any> = [{backgroundColor: [
    '#ff9999', '#99b3ff', '#d1b3ff', '#b3fff0', '#669900', '#99ff99', '#ffcc99'
  ]}];
  public pieChartType:string = 'pie';
  date:Date;
  constructor() {  }

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
  }
}
