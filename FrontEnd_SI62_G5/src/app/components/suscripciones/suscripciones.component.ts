import { Component } from '@angular/core';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatMenuModule } from '@angular/material/menu';
import { MatToolbarModule } from '@angular/material/toolbar';
import { ActivatedRoute, RouterModule, RouterOutlet } from '@angular/router';
import { ListarsuscripcionesComponent } from './listarsuscripciones/listarsuscripciones.component';

@Component({
  selector: 'app-suscripciones',
  standalone: true,
  imports: [RouterOutlet,ListarsuscripcionesComponent,RouterModule,MatToolbarModule,MatIconModule,MatMenuModule,MatButtonModule],
  templateUrl: './suscripciones.component.html',
  styleUrl: './suscripciones.component.css'
})
export class SuscripcionesComponent {
  constructor(public route:ActivatedRoute){}
}
