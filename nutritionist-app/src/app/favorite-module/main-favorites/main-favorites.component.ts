import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material';
import { MealService } from 'src/app/meal-module/meal.service';
import { UserMessageComponent } from 'src/app/user/user-message/user-message.component';
import { FavoriteService } from '../favorite.service';

@Component({
  selector: 'app-main-favorites',
  templateUrl: './main-favorites.component.html',
  styleUrls: ['./main-favorites.component.css']
})
export class MainFavoritesComponent implements OnInit {
  cards: any;
  user: string;
  id: number;
  favourites: any[];
  food: any;


  displayedColumns: string[] = ['name', 'amount', 'unitName'];
  dataSource: any;
  loading: boolean = false;
  constructor(private favoriteService: FavoriteService, public dialog: MatDialog, private mealService: MealService) {
    this.favourites = [];
  }

  ngOnInit() {
    this.getFavourites();
  }

  getFavourites() {
    const user = sessionStorage.getItem('user');
    this.favoriteService.getFavourites(user).subscribe(res => {
      this.favourites = res;
    }, (err) => {
      console.log(err);
      this.showMessage("Issue!! While fetching data. Please contact support team.")
    })
  }

  showMessage(msg: String): void {
    const dialogRef = this.dialog.open(UserMessageComponent, {
      data: { message: msg }
    });
    dialogRef.afterClosed().subscribe(result => {
    });
  }



  deleteFavourites(id: number) {
    this.favoriteService.deleteFavourites(id).subscribe(res => {
      console.log('Deleted');
      this.getFavourites();
    }, (err) => {
      console.log(err);
      this.showMessage("Issue!! While deletig data. Please contact support team.")
    })
  }

  openDialog(food: any){

    this.loading = true;
    this.food = '';
    this.favoriteService.searchFood(food.fdcId).subscribe(res => {
      this.food = res;
      this.dataSource = res.foodNutrients;
      console.log(this.food);
      this.loading = false;
    }, (err) => {
      console.log(err);
      this.showMessage("Issue!! While fetching data. Please contact support team.");
      this.loading = false;
    })
  }

  addMeal(food: any) {
    this.mealService.addMeal(food).subscribe(res => {
      console.log("Food has been added to your meal");
      this.showMessage(food.description + " - Food has been added to meal successfully.")
    }, (err) => {
      console.log(err);
      if (err.status == 409) {
        this.showMessage(err.error)
      } else {
        this.showMessage("Issue!! While adding to meal. Please contact support team.");
      }
    })
  }

  deleteFavorite(id: any){
    this.favoriteService.deleteFavourites(id).subscribe(res => {
      this.showMessage("Record deleted successfully");
      this.getFavourites();
    }, err => {
      this.showMessage("Issue!! While deletig. Please contact support team.")
    })
  }

}
