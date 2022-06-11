import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AuthguardService } from './authentication/authguard.service';
import { AuthguardServiceNew } from './authentication/authguard1.service';
import { AboutusComponent } from './common/aboutus/aboutus.component';
import { HomeComponent } from './common/home/home.component';
import { MainFavoritesComponent } from './favorite-module/main-favorites/main-favorites.component';
import { MainMealComponent } from './meal-module/main-meal/main-meal.component';
import { MainSearchComponent } from './search-module/main-search/main-search.component';
import { UserBmiComponent } from './user/user-bmi/user-bmi.component';
import { UserForgotpasswordComponent } from './user/user-forgotpassword/user-forgotpassword.component';
import { UserSigninComponent } from './user/user-signin/user-signin.component';
import { UserSignupComponent } from './user/user-signup/user-signup.component';


const routes: Routes = [
  {
    path: 'search-food',
    component: MainSearchComponent,
    canActivate:[AuthguardService]
  },
  {
    path : 'signup',
    component : UserSignupComponent,
    canActivate:[AuthguardServiceNew]
  },
  {
    path : 'signin',
    component : UserSigninComponent,
    canActivate:[AuthguardServiceNew]
  },
  {
    path : '',
    component: HomeComponent
  },
  {
    path : 'home',
    component: HomeComponent
  },
  {
    path : 'forgotpassword',
    component : UserForgotpasswordComponent,
    canActivate:[AuthguardServiceNew]
  },
  {
    path: 'bmi',
    component: UserBmiComponent,
    canActivate:[AuthguardService]
  },
  {
    path : 'aboutus',
    component : AboutusComponent
  },
  {
    path : 'mymeal',
    component : MainMealComponent,
    canActivate:[AuthguardService]
  },
  {
    path : 'favorites',
    component : MainFavoritesComponent,
    canActivate:[AuthguardService]
  },
  { path: '**', redirectTo: 'home'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
