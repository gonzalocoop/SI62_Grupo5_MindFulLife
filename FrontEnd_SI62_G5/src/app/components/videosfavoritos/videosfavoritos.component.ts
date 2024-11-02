import { Component } from '@angular/core';
import { ActivatedRoute, RouterOutlet, RouterModule } from '@angular/router';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatIconModule } from '@angular/material/icon';
import { MatMenuModule } from '@angular/material/menu';
import { MatButtonModule } from '@angular/material/button';
import { ListarvideosfavoritosComponent } from "./listarvideosfavoritos/listarvideosfavoritos.component";

@Component({
  selector: 'app-videosfavoritos',
  standalone: true,
  imports: [
    RouterOutlet,
    ListarvideosfavoritosComponent,
    MatToolbarModule,
    MatIconModule,
    MatMenuModule,
    MatButtonModule,
    RouterModule,
  ],
  templateUrl: './videosfavoritos.component.html',
  styleUrl: './videosfavoritos.component.css'
})
export class VideosfavoritosComponent {
  constructor(public route: ActivatedRoute) {}

}
