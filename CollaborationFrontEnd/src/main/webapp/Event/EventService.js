'use strict'

app.factory('EventService',['$http', '$q','$rootScope', function($http,$q,$rootScope){
	console.log("EventService...")
	
	var BASE_URL="http://localhost:8080/Collaboration"
		return{
		fetchAllUsers: function(){
			return $http.get(BASE_URL+'/Events')
			.then(
					function(response){
						return response.data;
					},
					function(errResponse){
						console.error('Error while fetching Eventdetails');
						return $q.reject(errResponse);
					}
					);
		},

		createEvent: function(event){
			
			return $http.post(BASE_URL+'/ERegister/',event)
			.then(function(response){
				if(response.data.errorMessage==""){
					$rootScope.currentEvent = {
							
							event_Id : response.data.event_Id,
							event_Name : response.data.event_Name,
							event_Venue : response.data.event_Venue,
							event_Description :response.data.event_Description,
							dateTime : response.data.dateTime
					};
				}
				return response.data;
			},
			function(errResponse){
				console.error('Error while creating Event');
				return $q.reject(errResponse);
			});
		}


}
}])