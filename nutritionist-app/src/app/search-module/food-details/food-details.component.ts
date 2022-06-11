import { Component, Inject, OnInit } from '@angular/core';
import { MatDialog, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';
import { FavoriteService } from 'src/app/favorite-module/favorite.service';
import { MealService } from 'src/app/meal-module/meal.service';
import { UserMessageComponent } from 'src/app/user/user-message/user-message.component';

@Component({
  selector: 'app-food-details',
  templateUrl: './food-details.component.html',
  styleUrls: ['./food-details.component.css']
})
export class FoodDetailsComponent {
  protien: any;
  energy: any;
  carbohydrate: any;
  displayedColumns: string[] = ['nutrientName', 'value', 'unitName'];
  dataSource: any;
  constructor(
    public dialogRef: MatDialogRef<FoodDetailsComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any,
    public dialog: MatDialog, public mealService: MealService, public favoriteService: FavoriteService
  ) {
    this.protien = data.food.foodNutrients.filter(n => n.nutrientName === 'Protein')[0]
    this.energy = data.food.foodNutrients.filter(n => n.nutrientName === 'Energy')[0];
    this.carbohydrate = data.food.foodNutrients.filter(n => n.nutrientName === 'Carbohydrate, by difference')[0];
    this.dataSource = data.food.foodNutrients;
  }

  onNoClick(): void {
    this.dialogRef.close();
  }

  addFavorite(food: any) {
    const req: any = {
      "fdcId": food.fdcId,
      "description": food.description,
      "publishedDate": food.publishedDate,
      "brandOwner": food.brandOwner,
      "score": food.score,
      "username": sessionStorage.getItem('user')
    }
    this.favoriteService.addFavourites(req).subscribe(res => {
      console.log("Food has been added to Favorites");
      this.showMessage(food.description + " - Food has been added to Favorites successfully.")
    }, (err) => {
      console.log(err);
      if (err.status == 409) {
        this.showMessage(err.error)
      } else {
        this.showMessage("Issue!! While adding to Favorites. Please contact support team.")
      }

    })
  }

  addMeal(food: any) {
    const req: any = {
      "fdcId": food.fdcId,
      "description": food.description,
      "publishedDate": food.publishedDate,
      "brandOwner": food.brandOwner,
      "score": food.score,
      "username": sessionStorage.getItem('user')
    }
    this.mealService.addMeal(req).subscribe(res => {
      console.log("Meal has been added to account");
      this.showMessage(food.description + " - Food has been added to Meal successfully.")
    }, (err) => {
      console.log(err);
      if (err.status == 409) {
        this.showMessage(err.error)
      } else {
        this.showMessage("Issue!! While adding to meal. Please contact support team.")
      }

    })
  }
  showMessage(msg: String): void {
    const dialogRef = this.dialog.open(UserMessageComponent, {
      data: { message: msg }
    });
    dialogRef.afterClosed().subscribe(result => {
    });
  }
}
