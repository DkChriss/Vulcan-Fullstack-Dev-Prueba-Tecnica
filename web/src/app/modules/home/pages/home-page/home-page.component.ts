import { Component, OnInit } from '@angular/core';
import { MatButtonModule } from '@angular/material/button';
import { MatRippleModule } from '@angular/material/core';
import { MatIconModule } from '@angular/material/icon';
import { MatMenuModule } from '@angular/material/menu';
import { MatTabsModule } from '@angular/material/tabs';
import { HomeService } from '../../services/home.service';
import * as echarts from 'echarts';
import { CommonModule } from '@angular/common';
import { EchartsxModule } from 'echarts-for-angular';
import { BarChart } from "echarts/charts";
import { TooltipComponent, GridComponent, LegendComponent } from "echarts/components";
import { round } from 'lodash';

@Component({
  selector: 'app-home-page',
  standalone: true,
  imports: [
    CommonModule,
    MatIconModule,
    MatButtonModule,
    MatRippleModule,
    MatMenuModule,
    MatTabsModule,
    EchartsxModule

  ],
  templateUrl: './home-page.component.html',
  styleUrl: './home-page.component.scss'
})
export class HomePageComponent implements OnInit{

    //CHARTS
    readonly echartsExtentions: any[];
    echartsAverageGender: object = {};
    echartsCapacity: object = {};

    courses_x: string [] = []
    female_avg: any [] = []
    male_avg: any [] = []
    porc_capacity_course: any [] = []
    //CARDS
    totalCourses;
    totalCompleteCourses;
    totalStudents;
    constructor(
        private _homeService: HomeService,
    ) {
        this.echartsExtentions = [BarChart, TooltipComponent, GridComponent, LegendComponent];
    }

    ngOnInit(): void {
        this.getData();
        this.getGeneralData();
    }

    getData() {
        this._homeService.home().subscribe({
            next: (resp:any) => {
                this.totalCourses = resp.data.totalCourses;
                this.totalCompleteCourses = resp.data.totalCompleteCourses;
                this.totalStudents = resp.data.totalStudents;
            },
            error: (error) => {
                console.log(error)
            }
        });
    }

    getGeneralData() {
        this._homeService.generalCourses().subscribe({
            next: (resp: any) => {
                let data = resp.data
                data.courses.map(obj => {
                    this.courses_x.push(obj.name);
                    this.female_avg.push(obj.averageGenderFemale);
                    this.male_avg.push(obj.averageGenderMale);
                    this.porc_capacity_course.push(obj.averageCapacity);
                });
                this.initChart();
            },
            error: (error) => {
                console.log(error)
            }
        });
    }

    initChart(): void {
        this.echartsAverageGender = {
            legend: {
                selectedMode: true
            },
            yAxis: {
                type: 'value'
            },
            xAxis: {
                type: 'category',
                data: this.courses_x
            },
            series: [
                {
                    name: "Media de mujeres",
                    type: 'bar',
                    stack: 'total',
                    barWidth: '60%',
                    label: {
                        show: true,
                        formatter: (params) => round(params.value,0) + '%'
                    },
                    data: this.female_avg
                },
                {
                    name: "Media de varones",
                    type: 'bar',
                    stack: 'total',
                    barWidth: '60%',
                    label: {
                        show: true,
                        formatter: (params) => round(params.value,0) + '%'
                    },
                    data: this.male_avg
                }
            ]
        };

        this.echartsCapacity = {
            yAxis: {
                type: 'value'
            },
            xAxis: {
                type: 'category',
                data: this.courses_x
            },
            series: [
                {
                    name: "Capacity",
                    type: 'bar',
                    stack: 'total',
                    barWidth: '50%',
                    label: {
                        show: true,
                        formatter: (params) => round(params.value,0) + '%'
                    },
                    data: this.porc_capacity_course
                },
            ]

        }
    }
}
