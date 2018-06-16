'use strict';

angular.module('core.symptom').service('SymptomService', function($http) {
		this.getAll = () => {
			return $http.get('/api/symptom/');
		};
		
		this.add = (data) => {
			return $http.post('/api/symptom/', data);
		};
		
		this.delete = (id) => {
			return $http.delete(`/api/symptom/${id}`);
		}
});