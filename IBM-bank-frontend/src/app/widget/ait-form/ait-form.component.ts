import { Component, OnInit, Input, Inject } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import {
  MAT_MOMENT_DATE_FORMATS,
  MomentDateAdapter,
  MAT_MOMENT_DATE_ADAPTER_OPTIONS,
} from '@angular/material-moment-adapter';
import { DateAdapter, MAT_DATE_FORMATS, MAT_DATE_LOCALE } from '@angular/material/core';

export interface FormField {
  name?: string;                     // nome do campo
  type: FieldType;                   // tipo do campo
  formControlName: string;           // nome do formControlName
  fieldPlaceholder?: string;         // texto do placeholder
  fieldFlexGrow?: number;            // flex-grow do campo
  fieldMinWidth?: string;            // largura mínima do campo
  fieldWidth?: string;               // largura do campo
  options?: [string, any][];         // opções para campos do tipo 'select'
  validators?: availableValidators;  // validadores do campo
}
type availableValidators = {
  maxLength?: number; maskPattern?: string;
}
type FieldType = "text" | "longText" | "number" | "select" | "buttonToggle" | "date" | "radio" | "checkbox";
export type TableFormFields = (FormField|undefined)[][];


@Component({
  standalone: false,
  selector: 'ait-form',
  templateUrl: './ait-form.component.html',
  styleUrls: ['./ait-form.component.scss'],
  providers: [
    { provide: MAT_DATE_LOCALE, useValue: 'pt-BR'},
    {
      provide: DateAdapter,
      useClass: MomentDateAdapter,
      deps: [MAT_DATE_LOCALE, MAT_MOMENT_DATE_ADAPTER_OPTIONS],
    },
    {provide: MAT_DATE_FORMATS, useValue: MAT_MOMENT_DATE_FORMATS},
  ],
})
export class AitFormComponent implements OnInit {

  @Input() columnGap: number = 20;
  @Input() TableFormFields: TableFormFields = [];
  @Input() formgroup: FormGroup = this.fb.group({});
  formgroupExample: FormGroup = this.fb.group({
    name: [{value:'', disabled: true},[Validators.required]],
    surname: ['', [Validators.maxLength(8)]],
    email: [''],
    userpassword: [''],
    concorda: [''],
    cidade: [null],
  });
  passwordHide = true;

  TableFormFieldsExample: TableFormFields = [
    [
      { name: 'nome', type: 'text', formControlName: 'name', fieldFlexGrow: 2, fieldMinWidth: '200px', validators: {maskPattern: '000-000-000'}},
      { name: 'sobrenome', type: 'text', formControlName: 'surname', fieldFlexGrow: 1, validators: {maxLength: 8}},
    ],
    [
      { name: 'senha', type: 'text', formControlName: 'userpassword', fieldWidth: '100%'},
    ],
    [
      { name: 'concordo com as regras', type: 'checkbox', formControlName: 'concorda', fieldWidth: '40%'},
    ],
    [
      {
        name: 'cidade',
        type: 'select',
        formControlName: 'cidade',
        fieldWidth: '500px',
        options: [
          ['são paulo', 'sao paulo'], 
          ['outras', ''],
          ['outras', ''],
        ]
      },
    ],
    [
      {
        name: 'cidade',
        type: 'radio',
        formControlName: 'cidade',
        fieldWidth: '100%',
        options: [
          ['são paulo', 'sao paulo'], 
          ['outras', ''],
          ['outras', ''],
        ]
      },
    ],
  ]

  constructor(private fb: FormBuilder,
    private _adapter: DateAdapter<any>,
    @Inject(MAT_DATE_LOCALE) private _locale: string,
  ) { }

  hasRequiredValidator(formControlName: string): boolean {
    if (!this.formgroup.controls[formControlName]) return false
    return this.formgroup.controls[formControlName].hasValidator(Validators.required);
  }

  ngOnInit(): void {
  }
}
