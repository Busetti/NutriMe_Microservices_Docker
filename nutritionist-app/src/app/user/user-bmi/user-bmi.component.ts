import { Component, OnInit } from '@angular/core';
import { UserService } from '../Service/user-service';

export interface BMITable {
  category: string;
  range: string;
}

const ELEMENT_DATA: BMITable[] = [
  {category: 'Severe Thinness', range: '< 16' },
  {category: 'Moderate Thinness', range: '16 - 17' },
  {category: 'Mild Thinness', range: '17 - 18.5' },
  {category: 'Normal', range: '18.5 - 25' },
  {category: 'Overweight', range: '25 - 30' },
  {category: 'Obese Class I', range: '30 - 35' },
  {category: 'Obese Class II', range: '35 - 40' },
  {category: 'Obese Class III', range: '> 40' }
];

@Component({
  selector: 'app-user-bmi',
  templateUrl: './user-bmi.component.html',
  styleUrls: ['./user-bmi.component.css']
})
export class UserBmiComponent implements OnInit {

  height: number;
  weight: number;
  height1: number;
  res: number;
  result = "";
  resultMessage = "";
  userEmail = "";

  displayedColumns: string[] = ['category', 'range'];
  dataSource = ELEMENT_DATA;
  
   
  constructor(private service: UserService) { }

  ngOnInit() {
    this.userEmail = sessionStorage.getItem('user');
    this.getUserInfo();
  }

  getUserInfo() {
    this.userEmail = sessionStorage.getItem('user');
    this.service.userInfo(this.userEmail).subscribe(data => {
      this.height = data.height;
      this.weight = data.weight;
      this.calculateBmi();
    },
      error => {
        console.log(error);
      });
  }

  calculateBmi(){
    this.height1 = this.height/100 ;
    this.res = this.weight / (this.height1*this.height1);
    this.result = this.res.toFixed(2) + " kg/m2";
    if(this.res <= 18.5){
       this.resultMessage = this.res <= 16 ? "Severe Thinness" : (this.res <= 17 ? "Moderate Thinness" : "Mild Thinness");
    }
    else if(this.res > 18.5 && this.res <= 25){
       this.resultMessage = "Normal";
    }
    else if(this.res > 25 && this.res <= 30){
       this.resultMessage = "Overweight";
    }
    else{
      this.resultMessage = this.res <= 35 ? "Obese Class I" : (this.res <= 40 ? "Obese Class II" : "Obese Class III");
    }
    console.log(this.result);
  }

}
