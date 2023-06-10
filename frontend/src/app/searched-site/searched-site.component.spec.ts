import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SearchedSiteComponent } from './searched-site.component';

describe('SearchedSiteComponent', () => {
  let component: SearchedSiteComponent;
  let fixture: ComponentFixture<SearchedSiteComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [SearchedSiteComponent]
    });
    fixture = TestBed.createComponent(SearchedSiteComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
