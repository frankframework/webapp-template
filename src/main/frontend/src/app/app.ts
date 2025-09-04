import { Component, inject, OnInit } from '@angular/core';
import { TestResponse, TestService } from './services/test.service';

@Component({
  selector: 'app-root',
  imports: [],
  templateUrl: './app.html',
  styleUrl: './app.css'
})
export class App implements OnInit {
  private readonly testService: TestService = inject(TestService);

  protected backendResponse?: string;

  ngOnInit(): void {
    this.testService.getHelloWorld().subscribe((response: TestResponse) => {
      this.backendResponse = response.data;
    })
  }

}
