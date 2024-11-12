import { Component, OnInit } from '@angular/core';
import { ChartDataset, ChartOptions, ChartType } from 'chart.js';
import { BaseChartDirective } from 'ng2-charts';
import { ComentariosService } from '../../../services/comentarios.service';

@Component({
  selector: 'app-top3cursos',
  standalone: true,
  imports: [BaseChartDirective],
  templateUrl: './top3cursos.component.html',
  styleUrl: './top3cursos.component.css'
})
export class Top3cursosComponent implements OnInit{
  barChartOptions: ChartOptions = {
    responsive: true,
  };
  barChartLabels: string[] = [];
  barChartType: ChartType = 'pie';
  barChartLegend = true;
  barChartData: ChartDataset[] = [];
  constructor(private cS: ComentariosService) {}
  ngOnInit(): void {
    this.cS.top3Cursos().subscribe((data) => {
      this.barChartLabels=data.map(item=>item.titulo)
      this.barChartData=
      [
        {
          data:data.map(item=>item.cantidad),
          label:'Cantidad de comentarios',
          backgroundColor:['#ee3007','#f08e79','#e98215'],
          borderColor:'#e94215',
          borderWidth:1
        }
      ]
    });
  }
}

