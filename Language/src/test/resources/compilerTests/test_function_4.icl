//5.5:float
let
    f:(ref float)float = fun x:ref float ->
        !x
    end
in
    print f(new 5.5)
end;;
