package main

import (
	"sort"
)

// Ordonnanceur selon la durée de calcul (shortest job first)
func sjf(newTasks []task) (currentTask *task) {
	for i := range newTasks {
		fcfsQueue = append(fcfsQueue, &(newTasks[i]))
	}
	sort.Slice(fcfsQueue, func(i, j int) bool {
		return fcfsQueue[i].Duration < fcfsQueue[j].Duration
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
