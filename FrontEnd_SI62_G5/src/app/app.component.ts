import { Component } from '@angular/core';
import { RouterModule, RouterOutlet } from '@angular/router';
import { CursosComponent } from './components/cursos/cursos.component';
import { VideoPlayerComponent } from './components/video-player/video-player.component';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatIconModule } from '@angular/material/icon';
import { MatMenuModule } from '@angular/material/menu';
import { MatButtonModule } from '@angular/material/button';
@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, CursosComponent, VideoPlayerComponent,MatToolbarModule,MatIconModule, MatMenuModule, MatButtonModule, RouterModule],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'FrontEnd_SI62_G5';
}
