import { Component, OnInit } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { MatDialog } from '@angular/material';
import { UserMessageComponent } from 'src/app/user/user-message/user-message.component';
import { MealService } from '../meal.service';

export interface RowElement {
  key: string;
  value: string;
}

@Component({
  selector: 'app-main-meal',
  templateUrl: './main-meal.component.html',
  styleUrls: ['./main-meal.component.css']
})
export class MainMealComponent implements OnInit {
  mealRes: any;
  reportLoading = false;
  report: any;
  energy: any;
  protein: any;

  constructor(private mealService: MealService, public dialog: MatDialog,
    private formBuilder: FormBuilder) { }
  mealForm = this.formBuilder.group({
    selectedMeal: ''
  });

  ngOnInit() {
    this.fetchMealByUsername();
  }

  displayedColumns: string[] = ["key", "value"];
  dataSource: RowElement[] = [];

  fetchMealByUsername() {
    this.mealRes = '';
    const username = sessionStorage.getItem('user');
    if (null != username || '' != username) {
      this.mealService.getMeals(username).subscribe(res => {
        this.mealRes = res;
      }, err => {
        this.showMessage('Issue while fetching data, Please contact support team')
      })
    }
  }

  showMessage(msg: String): void {
    const dialogRef = this.dialog.open(UserMessageComponent, {
      data: { message: msg }
    });
    dialogRef.afterClosed().subscribe(result => {
    });
  }

  onFormSubmit() {

    console.log(this.mealForm.get('selectedMeal').value);
    if (this.mealForm.get('selectedMeal').value !== '') {
      this.reportLoading = true;
      this.report = '';
      this.dataSource = [];
      const req: any = {
        "fdcIds": this.mealForm.get('selectedMeal').value,
        "format": "abridged",
        "nutrients": []
      }
      this.mealService.getReport(req).subscribe(res => {
        this.report = res;
        this.reportLoading = false;
        for (const key in this.report) {
          if(key === 'Energy (KCAL)'){
            this.energy = this.report[key];
          }
          if(key === 'Protein (G)'){
            this.protein = this.report[key];
          }
          this.dataSource.push({ key, value: this.report[key] });
        }

        console.log(JSON.stringify(this.dataSource));


      }, err => {
        this.reportLoading = false;
        this.showMessage('Issue while fetching data, Please contact support team')
      });
    } else {
      this.showMessage('Please select at least one meal');
    }
  }

  deleteMeal(id: any) {
    this.mealService.deleteMeal(id).subscribe(res => {
      this.mealRes.splice(this.mealRes.findIndex(a => a.id === id) , 1)
      this.showMessage('Deleted!! Successfully');

    }, err => {
      this.showMessage('Issue while deleting data, Please contact support team');
    })
  }
}
