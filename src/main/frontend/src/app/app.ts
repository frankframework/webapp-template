import { Component, inject, OnInit } from '@angular/core';
import { TestResponse, TestService } from './services/test.service';

@Component({
  selector: 'app-root',
  imports: [],
  templateUrl: './app.html',
  styleUrl: './app.scss',
})
export class App implements OnInit {
  protected backendResponse?: string;

  private readonly testService: TestService = inject(TestService);

  ngOnInit(): void {
    this.testService.getHelloWorld().subscribe((response: TestResponse) => {
      this.backendResponse = response.data;
    });
  }
}
