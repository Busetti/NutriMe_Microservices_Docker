import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material';
import { Router } from '@angular/router';
import { FavoriteService } from 'src/app/favorite-module/favorite.service';
import { MealService } from 'src/app/meal-module/meal.service';
import { UserMessageComponent } from 'src/app/user/user-message/user-message.component';
import { FoodDetailsComponent } from '../food-details/food-details.component';
import { SearchModuleService } from '../search-modue.service';

@Component({
  selector: 'app-main-search',
  templateUrl: './main-search.component.html',
  styleUrls: ['./main-search.component.css']
})
export class MainSearchComponent implements OnInit {

  foodInp: string;
  foodResponse: any;
  loading = false;
  isFavorite: boolean[] = [];
  favorites: any;

  constructor(private router: Router, private searchService: SearchModuleService,
    public dialog: MatDialog, public mealService: MealService, public favoriteService: FavoriteService) { }

  ngOnInit() {
    const user = sessionStorage.getItem('user');
    this.favoriteService.getFavourites(user).subscribe(res => {
      res.forEach(element => {
        this.isFavorite.push(element.fdcId);
      });
    })
  }

  searchFood() {
    this.loading = true;
    this.foodResponse = [];
    this.searchService.getFoods(this.foodInp).subscribe((res) => {
      if (res) {
        this.foodResponse = res;
        this.loading = false;
      }
    }, (error) => {
      console.log(error.status);
      this.loading = false;
      if (error.status === 401) {
        this.router.navigate(['signin']);
      }
    })
  }

  openDialog(food: any) {
    const dialogRef = this.dialog.open(FoodDetailsComponent, {
      width: '1050px',
      data: { food: food },
      backdropClass: 'backdropBackground'
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log(`Dialog result: ${result}`);
    });
  }
  isFavoritee(elem: any, event: any) {
    this.isFavorite[elem] = !this.isFavorite[elem];
    const user = sessionStorage.getItem('user');
    this.favoriteService.getFavourites(user).subscribe(res => {
      const favorite = res.filter(m => m.fdcId === elem)[0];
      if (favorite == undefined) {
        const food = this.foodResponse.foods.filter(m => m.fdcId === elem)[0];
        const req: any = {
          "fdcId": food.fdcId,
          "description": food.description,
          "publishedDate": food.publishedDate,
          "brandOwner": food.brandOwner,
          "score": food.score,
          "username": sessionStorage.getItem('user')
        }
        this.extractFavourite(req);
      }else{
        this.favoriteService.deleteFavourites(favorite.id).subscribe(res => {
          this.showMessage("Food has been removed from favorites.")
        })
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

  addFavorite(food: any) {
    const req: any = {
      "fdcId": food.fdcId,
      "description": food.description,
      "publishedDate": food.publishedDate,
      "brandOwner": food.brandOwner,
      "score": food.score,
      "username": sessionStorage.getItem('user')
    }
    this.extractFavourite(req);
  }

  extractFavourite(req: any) {
    this.favoriteService.addFavourites(req).subscribe(res => {
      console.log("Food has been added to Favorites");
      this.showMessage(req.description + " - Food has been added to Favorites successfully.")
    }, (err) => {
      console.log(err);
      if (err.status == 409) {
        this.showMessage(err.error)
      } else {
        this.showMessage("Issue!! While adding to Favorites. Please contact support team.")
      }
    });
  }

  showMessage(msg: String): void {
    const dialogRef = this.dialog.open(UserMessageComponent, {
      data: { message: msg }
    });
    dialogRef.afterClosed().subscribe(result => {
    });
  }


}

