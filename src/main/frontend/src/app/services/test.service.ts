import { inject, Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

export type TestResponse = {
  data: string;
};

@Injectable({
  providedIn: 'root',
})
export class TestService {
  private readonly http: HttpClient = inject(HttpClient);

  getHelloWorld(): Observable<TestResponse> {
    return this.http.get<TestResponse>('/api/test');
  }
}
