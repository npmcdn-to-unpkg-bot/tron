var tronApp = angular.module('tronApp', [ 'ngRoute', 'checklist-model',
		'angular.filter', 'ui.bootstrap', 'angular-confirm', 'ngJsTree','flow','toastr']);

tronApp.controller('DashboardController', function($scope, $rootScope, $http,
		$location, $confirm) {


	
	$http.get("/patches").success(function(data, status) {
		$scope.strarrays = data;
	});
	

});

tronApp.controller('DBPatchController', function($scope, $window, $location, $http){
	
	$scope.isCollapsed = true;
	
	$scope.metaData = null;
	$http.get("/systables.json").success(function(data, status){
		$scope.metaData = data;
	});
	
	/*
	$scope.dbObjects = [{
		objectType    : 'Algorithm',
		childerns        : [{table:'CI_BATCH_CTRL', inSw: 'Y', upSw: 'Y', dlSw :'N', frSw: 'Y'},{table:'CI_BATCH_CTRL_L', inSw: 'Y', upSw: 'Y', dlSw :'N', frSw: 'Y'}, {table:'CI_BATCH_CTRL_P', inSw: 'Y', upSw: 'Y', dlSw :'N', frSw: 'Y'}, {table:'CI_BATCH_CTRL_P_L', inSw: 'Y', upSw: 'Y', dlSw :'N', frSw: 'Y'}],
		condition     : 'OWNER_FLG = \'CM\'',
		exclution     : 'VERSION',
		isCollapsed   : true
	}];
	*/
	$scope.dbObjects = [];
	
   $scope.addDbObj = function(index){
	   $scope.isCollapsed = false;
	    //var dbObject = {

	    	//objectType : $scope.objectType,
	    	
	    	//condition  : $scope.condition,
	    	//exclution  : $scope.exclution
	    //};
	    //$scope.dbObjects.push(dbObject);
	   var dbObject = {};
	    $scope.dbObjects.splice(index+1,0, dbObject);
   }; 
	

   $scope.changeDD = function(dbObj) {
	   console.log(dbObj);
	   var tempObj = [];
	   
	   angular.forEach(dbObj.objectType, function(o){
		   var b = {};
		   b.condition = o.condition;
		   b.exclution = o.exclution;
		   b.table = o.table;
		   b.inSw = o.inSw;
		   b.upSw = o.upSw;
		   b.dlSw = o.dlSw;
		   b.frSw = o.frSw;
		   b.objName = o.objName;
		   
		   tempObj.push(b);
	   });
	   
	   //delete dbObj.objectType;
	   dbObj.objectType = tempObj;
	   
   }
   
   
   $scope.removeDbObj = function(index){
	    $scope.dbObjects.splice(index, 1);
	  }; 
	  
	$scope.saving = false;
	$scope.proceed = function() {
		
		$scope.saving = true;
		angular.forEach($scope.dbObjects, function(dbObject){
			
			delete dbObject.isCollapsed;
			
		});
		$http.post('/db-objects',{
			
			data: $scope.dbObjects
		}).
		success(function(data, status, headers){
			$scope.saving = false;
			$location.path('/review');
		});
		
		//$location.path('/review');
	}
	
	$scope.goBack = function() {
		$window.history.back();
	}
	
   
});


tronApp.controller('CreatePatchController', function($scope, $rootScope, $location, $http){
	
	$scope.environments = null;
	
	$http.get("/environments").success(function(data, status){
		$scope.environments = data;
	});
	
	$scope.proceed = function() {
		
		$rootScope.patch = $scope.patch;
		
		$http.post('/patches', {
			id   : $scope.patch.id,
			name : $scope.patch.name,
			desc : $scope.patch.desc,
			dest : $scope.patch.dest,
			env  : $scope.patch.env,
			createdBy : $scope.patch.createdBy,
			isAppIncl : $scope.patch.hasAppPatch,
			isDbIncl : $scope.patch.hasDbPatch,
			status   : 'Created'
		}).success(function(data, status, header){
			$scope.patch = data;
			$rootScope.patch = data;
		});
		
		
		if($scope.patch.hasAppPatch)
			$location.path('/apppatch');
		else if($scope.patch.hasDbPatch)
			$location.path('/dbpatch');
	}
});

tronApp.controller('AppPatchController', function($scope, $rootScope, $location, $window){
	
	
	$scope.selectedNode = null;
	$scope.treeConfig = {
            core : {
                multiple : false,
                animation: true,
                error : function(error) {
                    $log.error('treeCtrl: error from js tree - ' + angular.toJson(error));
                },
                check_callback : true,
                worker : true
            },
            version : 1
        };
	
	$scope.treeModelLeft = [{
		  "id": "cm",
		  "parent": "#",
		  "text": "cm"
		}, {
		  "id": "api",
		  "parent": "cm",
		  "text": "api"
		}, {
		  "id": "domain",
		  "parent": "cm",
		  "text": "domain"
		}, {
		  "id": "batch",
		  "parent": "domain",
		  "text": "batch"
		},
		{
		"id": "CmLockedMeterShowingConsumption_BatchProcess.java",
		"parent": "batch",
		"text": "CmLockedMeterShowingConsumption_BatchProcess.java",
		"icon": "jstree-icon jstree-file"
		},
		{
			"id": "CmOpenCloseDepositTenderControl_BatchProcess.java",
			"parent": "batch",
			"text": "CmOpenCloseDepositTenderControl_BatchProcess.java",
			"icon": "jstree-icon jstree-file"
		},
		{
			"id": "CmPledgePayReconIn_BatchProcess.java",
			"parent": "batch",
			"text": "CmPledgePayReconIn_BatchProcess.java",
			"icon": "jstree-icon jstree-file"
		},
		{
			  "id": "payment",
			  "parent": "domain",
			  "text": "payment"
		},
		{
			  "id": "tenderControl",
			  "parent": "payment",
			  "text": "tenderControl"
		},	
		{
			"id": "CmCheckDuplicatePayment.java",
			"parent": "tenderControl",
			"text": "CmCheckDuplicatePayment.java",
			"icon": "jstree-icon jstree-file"
			},
			{
				"id": "PaymentTender_CHandler.java",
				"parent": "tenderControl",
				"text": "PaymentTender_CHandler.java",
				"icon": "jstree-icon jstree-file"
				},			
		];
	
	$scope.treeModelRight = [{
		  "id": "cm",
		  "parent": "#",
		  "text": "cm"
		}, {
		  "id": "api",
		  "parent": "cm",
		  "text": "api"
		}, {
		  "id": "domain",
		  "parent": "cm",
		  "text": "domain"
		}, {
		  "id": "batch",
		  "parent": "domain",
		  "text": "batch"
		},
		{
		"id": "CmLockedMeterShowingConsumption_BatchProcess.java",
		"parent": "batch",
		"text": "CmLockedMeterShowingConsumption_BatchProcess.java",
		"icon": "jstree-icon jstree-file"
		}];	
	
	$scope.addNewNode = function() {
		$scope.treeModelRight.push({ id : '44321', parent : $scope.newNode.parent, text : $scope.newNode.text });
    };
	
	$scope.applyModelChanges = function() {
		console.log('Setting Apply Changes as True');
		return true;
	};
	
	$scope.selectNodeCB = function(e, list){
		console.log(list.node);
		$scope.selectedNode = list.node;
		console.log('Pushed Value..');
		
	};
	
	$scope.moveRight = function() {
		$scope.treeModelRight.push($scope.selectedNode);
	};
	
	$scope.proceed = function() {
		console.log('I am in');
		if($rootScope.patch.hasDbPatch)
			$location.path('/dbpatch');
	}
	
	$scope.goBack = function() {
		$window.history.back();
	}
	
});


tronApp.controller('ReviewController', function($scope, $rootScope, $location){
	
	$scope.inputFileUrl = function(){
		return '../storage/'+$rootScope.patch.name+'/input.inp';
	};
	
	$scope.selectedFiles = [{
		'file': 'CmLockedMeterShowingConsumption_BatchProcess.java',
		'folder': 'cm/com/splwg/cm/domain/batch'
	},{
		'file': 'CmOpenCloseDepositTenderControl_BatchProcess.java',
		'folder': 'cm/com/splwg/cm/domain/batch'
	},{
		'file': 'CmCheckDuplicatePayment.java',
		'folder': 'cm/com/splwg/cm/domain/payment/tenderControl'
	}]
	
});

tronApp.controller('AppObjectTypeController', function($scope, $rootScope,
		$http, $location, $confirm) {

	$scope.assessmentsTodelete = {
		assessments : []
	};

	$http.get("/appObjType").success(function(appObjTypes, status) {
		$scope.appObjTypes = appObjTypes;
	});

});
tronApp.controller('EnvironementeController', function($scope, $rootScope,
		$http, $location, $confirm, $window,hexafy, toastr) {

	$scope.environmentToShow = {
		environments : []
	};
	
	$scope.loadData = function() {
		
		$http.get("/environments").success(function(environments, status) {
			$scope.environments = environments;
		});		
	};
	
	//initail load
	$scope.loadData();


	$scope.saveEnvironment = function saveEnvironment() {
		$http.post('/environments', $scope.environment).success(
				function(data, status, headers) {
					//$window.location.reload();;
					//alert('Configuration Saved');
					$('#myModal').modal('toggle');
					toastr.success('Configuration Saved!', 'Alert!');
					$scope.loadData();

				}).error(function(data, status, headers) {
			//alert('Configuration Error');
					toastr.error('Error occured while saving the configuration!', 'Alert!');
		});
	}

	$scope.deleteEnvironment = function deleteEnvironment(environment) {
		$http.post('/environment/delete', environment).success(function(data, status, headers) {
					console.log(">> Deleted..");
					toastr.success('Configuration Deleted!', 'Alert!');
					$scope.loadData();
				}).error(function(data, status, headers) {
					toastr.error(data.message, 'Error Deleting Entry!');
			
		});
	}

});

tronApp.service('hexafy', function() {
    this.myFunc = function (x) {
    	$('#errorPopup').modal('toggle');
    }
});

tronApp.controller('ModalDemoCtrl', function ($scope, $uibModal, $log) {

	  $scope.items = ['item1', 'item2', 'item3'];

	  $scope.animationsEnabled = true;

	  $scope.open = function (size) {

	    var modalInstance = $uibModal.open({
	      animation: $scope.animationsEnabled,
	      templateUrl: 'myModalContent.html',
	      controller: 'ModalInstanceCtrl',
	      size: size,
	      resolve: {
	        items: function () {
	          return $scope.items;
	        }
	      }
	    });

	    modalInstance.result.then(function (selectedItem) {
	      $scope.selected = selectedItem;
	    }, function () {
	      $log.info('Modal dismissed at: ' + new Date());
	    });
	  };

	  $scope.toggleAnimation = function () {
	    $scope.animationsEnabled = !$scope.animationsEnabled;
	  };

	});

	// Please note that $uibModalInstance represents a modal window (instance) dependency.
	// It is not the same as the $uibModal service used above.

tronApp.controller('ModalInstanceCtrl', function ($scope, $uibModalInstance, items) {

	  $scope.items = items;
	  $scope.selected = {
	    item: $scope.items[0]
	  };

	  $scope.ok = function () {
	    $uibModalInstance.close($scope.selected.item);
	  };

	  $scope.cancel = function () {
	    $uibModalInstance.dismiss('cancel');
	  };
	});

/*
 * ---- Routing Configurations ----
 * 
 * All Routing Configurations will provided here
 * 
 */
tronApp.config(function($routeProvider) {
	$routeProvider.when('/', {
		templateUrl : './pages/dashboard.html',
		controller : 'DashboardController'
	}).when('/appObjType', {
		templateUrl : './pages/databaseTemplate.html',
		controller : 'AppObjectTypeController'
	})
	.when('/createpatch', {
		templateUrl: './pages/createpatch.html',
		controller: 'CreatePatchController'
	})

	.when('/dbpatch', {
		templateUrl: './pages/dbpatch.html',
		controller: 'DBPatchController'
	})
	
	.when('/apppatch', {
		templateUrl: './pages/apppatch.html',
		controller: 'AppPatchController'
	})	
	.when('/review', {
		templateUrl: './pages/review.html',
		controller: 'ReviewController'
	})		
	.when('/manage-env', {
		templateUrl : './pages/environment.html',
		controller : 'EnvironementeController'
	}).when('/testTree', {
		templateUrl : './pages/treeTest.html',
		controller : 'TreeController'
	})

});

tronApp.config(function(toastrConfig){
	angular.extend(toastrConfig, {
		newestOnTop: true,
		positionClass: 'toast-bottom-right'
	});
	
});
