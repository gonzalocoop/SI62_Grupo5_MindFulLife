import { Component } from '@angular/core';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatMenuModule } from '@angular/material/menu';
import { MatToolbarModule } from '@angular/material/toolbar';
import { RouterModule, RouterOutlet } from '@angular/router';

@Component({
  selector: 'app-reportes',
  standalone: true,
  imports: [RouterOutlet,RouterModule,MatToolbarModule,MatIconModule, MatMenuModule, MatButtonModule ],
  templateUrl: './reportes.component.html',
  styleUrl: './reportes.component.css'
})
export class ReportesComponent {

}
