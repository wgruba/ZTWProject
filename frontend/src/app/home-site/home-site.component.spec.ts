import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HomeSiteComponent } from './home-site.component';

describe('HomeSiteComponent', () => {
  let component: HomeSiteComponent;
  let fixture: ComponentFixture<HomeSiteComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [HomeSiteComponent]
    });
    fixture = TestBed.createComponent(HomeSiteComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
