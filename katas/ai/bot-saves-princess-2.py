#

def nextMove(n,r,c,grid):
    # Positions have the format (row, column) with -1 
    # indicating unknown
    botPosition = (r, c)
    princessPosition = (-1, -1)
    
    # Search each row for the princess.
    # Once found, return and compare with the
    # bot's current position.
    for row in range(len(grid)):
        rowValue = grid[row]
        princessColumn = rowValue.find("p")
        
        if(princessColumn >= 0):
            princessPosition = (row, princessColumn)
            break

    # Return the first move from the bot towards the princess
    # Positions have the format (row, column)
    if(botPosition[1] < princessPosition[1]):
        return "RIGHT"
    elif(botPosition[1] > princessPosition[1]):
        return "LEFT"
    # The top of the grid starts at 0, so if the bot has a lower
    # row than the princess, then we need to move down
    elif(botPosition[0] < princessPosition[0]):
        return "DOWN"
    elif(botPosition[0] > princessPosition[0]):
        return "UP"
    
    # The bot and princess can't be in the same position,
    # so something must have gone wrong
    return ""

n = int(input())
r,c = [int(i) for i in input().strip().split()]
grid = []
for i in range(0, n):
    grid.append(input())

print(nextMove(n,r,c,grid))