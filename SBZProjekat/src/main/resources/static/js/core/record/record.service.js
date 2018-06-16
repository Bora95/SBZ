'use strict';

angular.module('core.record').service('RecordService', function($http) {
		this.getAll = () => {
			return $http.get('/api/record/');
		};
		
		this.get = (jmbg) => {
			return $http.get(`/api/record/${jmbg}`);
		};
		
		this.add = (data) => {
			return $http.post('/api/record/', data);
		};
		
		this.addDiagnose = (jmbg, data) => {
			return $http.put(`/api/record/${jmbg}`, data);
		};
		
		this.delete = (jmbg) => {
			return $http.delete(`/api/symptom/${jmbg}`);
		}
});