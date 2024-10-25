import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { CursosComponent } from './components/cursos/cursos.component';
import { VideoPlayerComponent } from './components/video-player/video-player.component';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet,CursosComponent,VideoPlayerComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'FrontEnd_SI62_G5';
}
