'use strict';

app.controller('EventController',['$http','$scope','EventService','$location','$rootScope','$cookieStore',
       function($http,$scope,EventService,$location,$rootScope,$cookieStore){
	console.log("EventController...")
	var self = this;
	self.event = {
			event_Id : '',
			event_Name : '',
			event_Venue : '',
			event_Description : '',
			dateTime : '',
			errorMessage:'',
			errorCode:''
	};
	
self.events = [];
$scope.eimg="";
self.submit=function(){
	console.log('submitting the event details')
	self.createEvent(self.event);
	console.log("event is "+self.event  )
};
	        
			
	        self.fetchAllevents = function(){
				EventService.fetchAllEvents().then(function(d){
					self.events = d;
				}, function(errResponse){
					console.error('Error while fetching events');
				});
			};
	
	self.createEvent = function(){
		console.log("createEvent...")
		EventService
		   .createEvent(self.event)
		   .then(function(d){
			   $rootScope.currentEvent = {
					   
						event_Id : self.event.event_Id,
						event_Name : self.event.event_Name,
						event_Venue : self.event.event_Venue,
						event_Description :self.event.event_Description,
						dateTime : self.event.dateTime
			    		 };
			   console.log('event name is '+$rootScope.currentEvent.event_Name);
      			$location.path('/eventimage');
      			
      		},
				function(errResponse)
				{
			console.error('Error while creating event');
		});
	};
}])