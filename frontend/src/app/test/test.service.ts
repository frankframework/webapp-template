import { inject, Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { TestResponse } from '../models/test-response';

@Injectable({
  providedIn: 'root'
})
export class TestService {

  private readonly http: HttpClient = inject(HttpClient);

  getHelloWorld(): Observable<TestResponse> {
    return this.http.get<TestResponse>('http://localhost:8080/api/test');
  }
}
