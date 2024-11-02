import { Component } from '@angular/core';
import { ActivatedRoute, RouterOutlet, RouterModule } from '@angular/router';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatIconModule } from '@angular/material/icon';
import { MatMenuModule } from '@angular/material/menu';
import { MatButtonModule } from '@angular/material/button';
import { ListarusuariossuscripcionesComponent } from "./listarusuariossuscripciones/listarusuariossuscripciones.component";

@Component({
  selector: 'app-usuariossuscripciones',
  standalone: true,
  imports: [
    RouterOutlet,
    ListarusuariossuscripcionesComponent,
    MatToolbarModule,
    MatIconModule,
    MatMenuModule,
    MatButtonModule,
    RouterModule,
  ],
  templateUrl: './usuariossuscripciones.component.html',
  styleUrl: './usuariossuscripciones.component.css'
})
export class UsuariossuscripcionesComponent {
  constructor(public route: ActivatedRoute) {}

}
