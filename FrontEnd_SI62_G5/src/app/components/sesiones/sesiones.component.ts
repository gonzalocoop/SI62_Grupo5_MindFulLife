import { Component } from '@angular/core';
import { ActivatedRoute, RouterOutlet, RouterModule } from '@angular/router';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatIconModule } from '@angular/material/icon';
import { MatMenuModule } from '@angular/material/menu';
import { MatButtonModule } from '@angular/material/button';
import { ListarsesionesComponent } from './listarsesiones/listarsesiones.component';
import { ListarvideosComponent } from "../videos/listarvideos/listarvideos.component";

@Component({
  selector: 'app-sesiones',
  standalone: true,
  imports: [
    RouterOutlet,
    ListarsesionesComponent,
    MatToolbarModule,
    MatIconModule,
    MatMenuModule,
    MatButtonModule,
    RouterModule,
    ListarvideosComponent
],
  templateUrl: './sesiones.component.html',
  styleUrl: './sesiones.component.css'
})
export class SesionesComponent {
  constructor(public route: ActivatedRoute) {}

}
