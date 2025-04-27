import { Component, OnInit } from '@angular/core';
import { HelloWorldService } from '../hello-world.service';

@Component({
  selector: 'app-hello-world',
  templateUrl: './hello-world.component.html',
  styleUrls: ['./hello-world.component.scss']
})
export class HelloWorldComponent implements OnInit {
  message: string = 'Loading...';

  constructor(private helloService: HelloWorldService) {}

  ngOnInit(): void {
    this.helloService.getHelloMessage().subscribe({
      next: (msg) => this.message = msg,
      error: (err) => {
        console.log(err);
        this.message = 'Error: ' + err.message}
    });
  }
}
