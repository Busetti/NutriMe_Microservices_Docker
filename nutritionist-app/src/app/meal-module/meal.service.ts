import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class MealService {

  public API = `${environment.API_URL}`;

  public SAVE_MEAL = this.API + '/api/v1/meal/addmeal';
  public GET_MEALS = this.API + '/api/v1/meal/mealsbyusername';
  public ANALYZE = this.API + '/api/v1/meal/analyze';
  public DELETE_MEAL = this.API + '/api/v1/meal/deletemeal';

  constructor(private http: HttpClient) { }

  addMeal(req: any): Observable<any>{
    return this.http.post(`${this.SAVE_MEAL}`, req);
  }

  getMeals(username: any): Observable<any>{
    return this.http.get(`${this.GET_MEALS}` +'/'+username);
  }

  getReport(req: any): Observable<any>{
    return this.http.post(`${this.ANALYZE}`, req);
  }

  deleteMeal(id: any): Observable<any>{
    return this.http.delete(`${this.DELETE_MEAL}`+'/'+ id);
  }
}
