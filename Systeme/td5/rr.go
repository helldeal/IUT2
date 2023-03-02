package main

// Ordonnanceur à temps partagé avec politique du tourniquet (Round Robin)
// le quantum vaudra 5 unités de temps
var quantum int = 0

func rr(newTasks []task) (currentTask *task) {
	for i := range newTasks {
		fcfsQueue = append(fcfsQueue, &(newTasks[i]))
	}

	if fcfsCurrentTask == nil || quantum > 4 || fcfsCurrentTask.Duration <= 0 {
		if fcfsCurrentTask != nil && fcfsCurrentTask.Duration > 0 {
			fcfsQueue = append(fcfsQueue, fcfsCurrentTask)
		}
		if len(fcfsQueue) > 0 {
			fcfsCurrentTask = fcfsQueue[0]
			fcfsQueue = fcfsQueue[1:]
		} else {
			fcfsCurrentTask = nil
		}
		quantum = 0
	}
	quantum++

	return fcfsCurrentTask
}
