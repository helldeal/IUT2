package main

var fcfsQueue []*task
var fcfsCurrentTask *task

// Ordonnanceur premier arrivé premier servi (first come first served)
func fcfs(newTasks []task) (currentTask *task) {
	for i := range newTasks {
		fcfsQueue = append(fcfsQueue, &(newTasks[i]))
	}
	if fcfsCurrentTask == nil || fcfsCurrentTask.Duration <= 0 {
		if len(fcfsQueue) > 0 {
			fcfsCurrentTask = fcfsQueue[0]
			fcfsQueue = fcfsQueue[1:]
		} else {
			fcfsCurrentTask = nil
		}
	}
	return
}
