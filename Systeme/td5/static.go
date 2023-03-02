package main

import "sort"

// Ordonnanceur à priorités statiques
func static(newTasks []task) (currentTask *task) {
	for i := range newTasks {
		fcfsQueue = append(fcfsQueue, &(newTasks[i]))
	}
	sort.Slice(fcfsQueue, func(i, j int) bool {
		return fcfsQueue[i].Priority < fcfsQueue[j].Priority
	})
	if fcfsCurrentTask == nil || fcfsCurrentTask.Duration <= 0 {
		if len(fcfsQueue) > 0 {
			fcfsCurrentTask = fcfsQueue[0]
			fcfsQueue = fcfsQueue[1:]
		} else {
			fcfsCurrentTask = nil
		}
	}
	return fcfsCurrentTask
}
